import appcenterLogo from "assets/appcenter-logo.svg";
import { useNavigate } from "react-router-dom";
import useUserStore from "userStore";

export default function Header() {
  const { email, clearUser } = useUserStore();
  const navigate = useNavigate();

  return (
    <header>
      <div className="flex h-36 items-center justify-between px-8">
        <img src={appcenterLogo} className="h-32" alt="Appcenter Logo" />
        <div className="flex flex-row gap-8">
          <span className="text-2xl">{email} 님</span>
          <button
            onClick={() => {
              clearUser();
              navigate("/login");
            }}
            className="text-2xl"
          >
            로그아웃
          </button>
        </div>
      </div>
      <h1 className="text-center text-7xl font-semibold text-customBlue">
        TODO LIST
      </h1>
    </header>
  );
}
