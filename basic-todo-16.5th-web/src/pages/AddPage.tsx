import { useState } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import calendar from "assets/calendar.svg";
import { useNavigate } from "react-router-dom";
import axiosInstance from "axiosInstance";
import useUserStore from "userStore";

export default function AddPage() {
  const navigate = useNavigate();
  const { memberId } = useUserStore();
  const [content, setContent] = useState("");
  const [selectedDate, setSelectedDate] = useState<Date | null>(new Date());

  const handleAddTask = async () => {
    if (!content.trim()) {
      alert("할 일을 입력해주세요.");
      return;
    }

    try {
      await axiosInstance.post("/api/todos", {
        memberId,
        content,
        deadLine: selectedDate
          ? selectedDate.toISOString().split("T")[0]
          : null,
      });

      alert("할 일이 추가되었습니다.");
      navigate("/list");
    } catch (error) {
      console.error(error);
      alert("할 일 추가에 실패했습니다.");
    }
  };

  return (
    <div className="m-auto my-16 flex w-3/4 flex-col gap-16">
      <h2 className="border-b-4 border-customBlue text-4xl font-medium text-customBlue">
        New Task
      </h2>
      <div>
        <h3 className="mb-4 text-3xl">Todo</h3>
        <input
          placeholder="할 일을 작성해주세요."
          value={content}
          onChange={(e) => setContent(e.target.value)}
          className="h-16 w-full rounded-xl border-2 border-customBlue p-3 text-xl"
        />
      </div>
      <div>
        <h3 className="mb-4 text-3xl">Date</h3>
        <span className="flex h-16 w-64 items-center gap-4 rounded-xl border-2 border-customBlue p-3 text-xl">
          <img src={calendar} className="h-6 w-6" alt="" />
          <DatePicker
            selected={selectedDate}
            onChange={(date) => setSelectedDate(date)}
            dateFormat="yyyy.MM.dd"
            className="w-48"
          />
        </span>
      </div>

      <div className="flex gap-4 self-center">
        <button
          onClick={() => navigate("/list")}
          className="h-16 w-48 rounded-xl border-2 border-customBlue text-2xl font-semibold text-customBlue"
        >
          취소
        </button>
        <button
          onClick={handleAddTask}
          className="h-16 w-48 rounded-xl bg-customBlue text-2xl font-semibold text-white"
        >
          할일 추가
        </button>
      </div>
    </div>
  );
}
