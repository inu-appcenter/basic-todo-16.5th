import { useState } from "react";
import { useNavigate } from "react-router-dom";
import appcenterLogoText from "assets/appcenter-logo-text.svg";
import axiosInstance from "axiosInstance";
import useUserStore from "userStore";

export default function LoginPage() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const setUser = useUserStore((state) => state.setUser);
  const navigate = useNavigate();

  const handleLogin = async () => {
    if (!email || !password) {
      alert("이메일과 비밀번호를 입력해주세요.");
      return;
    }

    try {
      const response = await axiosInstance.post("/api/members/login", {
        email,
        password,
      });
      const { email: userEmail, memberId } = response.data;
      setUser({ email: userEmail, memberId });
      navigate("/list");
    } catch (error) {
      alert("로그인에 실패했습니다. 다시 시도해주세요.");
      console.error(error);
    }
  };

  return (
    <div className="m-auto my-52 flex w-[768px] flex-col gap-16">
      <img src={appcenterLogoText} className="h-48" alt="appcenter" />
      <div>
        <h3 className="mb-4 text-2xl font-bold text-customBlue">EMAIL</h3>
        <input
          type="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          className="h-20 w-full rounded-md border-2 border-customBlue p-3 text-xl"
        />
      </div>
      <div>
        <h3 className="mb-4 text-2xl font-bold text-customBlue">PASSWORD</h3>
        <input
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          className="h-20 w-full rounded-md border-2 border-customBlue p-3 text-xl"
        />
      </div>
      <button
        onClick={handleLogin}
        className="h-20 bg-customBlue text-2xl font-bold text-white"
      >
        로그인
      </button>
    </div>
  );
}
