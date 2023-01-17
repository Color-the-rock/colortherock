import AppRouter from "./AppRouter";
import Footer from "./components/layout/Footer";
import Header from "./components/layout/Header";
function App() {
  return (
    <div>
      <Header />
      <AppRouter />
      <Footer />
    </div>
  );
}

export default App;
