#!/usr/bin/env bash
# 현재 사용중인 포트를 확인한다.
RESPONSE=$(curl -s -k -L colortherock.com/actuator/health)
echo "> RESPONSE : "$RESPONSE

IS_ACTIVE=$(echo ${RESPONSE} | grep 'UP' | wc -l)
echo "> IS_ACTIVE" "$IS_ACTIVE"
CURRENT_PORT=$(curl -k -L colortherock.com/port | grep 'BLUE' | wc -l)
echo " CURRENT PORT" "$CURRENT_PORT"
if [ "$IS_ACTIVE" -eq 1 ];
then
    if [ "$CURRENT_PORT" -eq 1 ];
    then
        IDLE_PORT=8081
        IDLE_PROFILE=GREEN
        CURRENT_PORT=8080
        CURRENT_PROFILE=BLUE
    else
        IDLE_PORT=8080
        IDLE_PROFILE=BLUE
        CURRENT_PORT=8081
        CURRENT_PROFILE=GREEN

    fi
else
    IDLE_PORT=8080
    IDLE_PROFILE=BLUE
    CURRENT_PORT=8081
    CURRENT_PROFILE=GREEN
fi

# 배포했던 서���들이 전부 성공적으로 빌드가 되었는지 확인한다.
SERVERS=(`cat servers`)
# 서버들의 개수
TOTAL_SERVER=${#SERVERS[@]}
# 임시로 Spring 서버들의 응답을 저장할 파일을 하나 만든다.
sudo true > servers_response
sudo chmod 664 servers_response

for server in ${SERVERS[*]} ; do
  # spring 서버들의 결과 파일들을 가져온다.
  # 결과 파일 가져온걸 servers_response에 저장
  sudo true > RESULT
  scp ${server}:RESULT .
  cat RESULT >> servers_response
echo "> SERVERS RESULT : $(cat servers_response)"
done

if [ "$TOTAL_SERVER" -eq "$(grep -c "$IDLE_PORT" servers_response)" ]; then
  echo "> 정상 배포 완료"
  echo "set \$active_server $IDLE_PROFILE;" | sudo tee /etc/nginx/default.d/port.conf
  echo "> nginx 재시작"
  sudo systemctl reload nginx
  # 스위칭 하기위해 spring 서버들의 docker 컨테이너를 내리자.
  for server in ${SERVERS[*]} ; do
    echo "> server : "$server
    echo "> ssh" $server docker kill "$(ssh $server docker ps -qf publish=$CURRENT_PORT)"
    ssh $server docker kill "$(ssh $server docker ps -qf publish=$CURRENT_PORT)" 2> /dev/null || echo "현재 실행중인 서버가 없습니다. CURRENT_PORT: $CURRENT_PORT"
  done
else
  echo "> 배포 실패"
  echo "스위치 하려고 켜놓은 서버 포트들을 전부 종료합니다."
  for server in ${SERVERS[*]} ; do
    echo "> server : "$server
    echo "> ssh $server docker kill "$(ssh $server docker ps -qf publish=$IDLE_PORT)""
    ssh $server docker kill "$(ssh $server docker ps -qf publish=$IDLE_PORT)" 2> /dev/null || echo "현재 실행중인 서버가 없습니다. CURRENT_PORT: $IDLE_PORT"
  done
fi
