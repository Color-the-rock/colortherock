import AppRouter from "./AppRouter";
import Footer from "./components/layout/Footer";
import Header from "./components/layout/Header";
import Template from "./components/layout/Template";
function App() {
  return (
    <div>
      <Header />
      <Template>
        <AppRouter />
      </Template>
      <Footer />
    </div>
  );
}

export default App;
