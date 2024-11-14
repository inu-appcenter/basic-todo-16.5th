import { Route, Routes, BrowserRouter } from "react-router-dom";
import RootPage from "pages/RootPage";
import ListPage from "pages/ListPage";
import AddPage from "pages/AddPage";
import LoginPage from "pages/LoginPage";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<RootPage />}>
          <Route path="/list" element={<ListPage />} />
          <Route path="/add" element={<AddPage />} />
        </Route>
        <Route path="/login" element={<LoginPage />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
