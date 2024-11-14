import { useState } from "react";
import pencil from "assets/pencil.svg";
import trashCan from "assets/trash-can.svg";
import axiosInstance from "axiosInstance";

interface Todo {
  todoId: number;
  content: string;
  deadLine: string;
  isCompleted: boolean;
}

interface TodoItemProps {
  todo: Todo;
  onDelete: () => void;
}

export default function TodoItem({ todo, onDelete }: TodoItemProps) {
  const [isChecked, setIsChecked] = useState(todo.isCompleted);
  const [isEditing, setIsEditing] = useState(false);
  const [content, setContent] = useState(todo.content);
  const [deadLine, setDeadLine] = useState(todo.deadLine);

  const handleCheckboxChange = async () => {
    try {
      await axiosInstance.put(`/api/todos/${todo.todoId}`, {
        content,
        deadLine,
        isCompleted: !isChecked,
      });
      setIsChecked(!isChecked);
    } catch (error) {
      console.error(error);
    }
  };

  const handleEdit = async () => {
    try {
      await axiosInstance.put(`/api/todos/${todo.todoId}`, {
        content,
        deadLine,
        isCompleted: isChecked,
      });
      setIsEditing(false);
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="flex h-24 items-center gap-6 rounded-2xl bg-white p-4 shadow">
      <label className="h-11 w-11">
        <input
          type="checkbox"
          checked={isChecked}
          onChange={handleCheckboxChange}
          className="hidden" // 실제 체크박스를 숨김
        />
        {/* 가짜 체크박스 스타일 */}
        <span
          className={`inline-block h-11 w-11 rounded-md border-2 border-customYellow ${
            isChecked ? "bg-customYellow" : "bg-white"
          }`}
        >
          {isChecked && (
            <svg
              xmlns="http://www.w3.org/2000/svg"
              viewBox="0 0 22 22"
              fill="currentColor"
              className="h-11 w-11 text-white"
            >
              <path
                fillRule="evenodd"
                d="M16.707 5.293a1 1 0 00-1.414 0L8 12.586 5.707 10.293a1 1 0 00-1.414 1.414l3 3a1 1 0 001.414 0l8-8a1 1 0 000-1.414z"
                clipRule="evenodd"
              />
            </svg>
          )}
        </span>
      </label>
      <div className="flex flex-1 flex-col">
        {isEditing ? (
          <>
            <input
              value={content}
              onChange={(e) => setContent(e.target.value)}
              className="mb-2 border-b text-xl"
            />
            <input
              type="date"
              value={deadLine}
              onChange={(e) => setDeadLine(e.target.value)}
              className="border-b text-xl"
            />
          </>
        ) : (
          <>
            <p
              className={`text-xl ${isChecked && "text-black text-opacity-50 line-through"}`}
            >
              {content}
            </p>
            <p
              className={`text-xl ${isChecked && "text-black text-opacity-50"}`}
            >
              {deadLine}
            </p>
          </>
        )}
      </div>
      <div className="flex gap-4">
        {isEditing ? (
          <button
            onClick={handleEdit}
            className="h-11 w-11 rounded-3xl bg-customSky"
          >
            완료
          </button>
        ) : (
          <span
            onClick={() => setIsEditing(true)}
            className="flex h-11 w-11 cursor-pointer items-center justify-center rounded-3xl bg-customSky"
          >
            <img src={pencil} className="h-6 w-6" alt="수정" />
          </span>
        )}
        <span
          onClick={onDelete}
          className="flex h-11 w-11 cursor-pointer items-center justify-center rounded-3xl bg-customSky"
        >
          <img src={trashCan} className="h-6 w-6" alt="삭제" />
        </span>
      </div>
    </div>
  );
}
