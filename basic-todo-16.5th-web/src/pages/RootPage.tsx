import Header from "components/Header";
import { useEffect } from "react";
import { Outlet, useNavigate } from "react-router-dom";
import useUserStore from "userStore";

export default function RootPage() {
  const navigate = useNavigate();
  const { email } = useUserStore();

  useEffect(() => {
    if (!email) {
      navigate("/login");
    }
  }, [email]);

  return (
    <div>
      <Header />
      <Outlet />
    </div>
  );
}
