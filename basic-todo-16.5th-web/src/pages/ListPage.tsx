import { useNavigate } from "react-router-dom";
import TodoItem from "components/TodoItem";
import { useEffect, useState } from "react";
import axiosInstance from "axiosInstance";
import useUserStore from "userStore";

interface Todo {
  todoId: number;
  content: string;
  deadLine: string;
  isCompleted: boolean;
}

export default function ListPage() {
  const navigate = useNavigate();
  const [todos, setTodos] = useState<Todo[]>([]);
  const { memberId } = useUserStore();

  const fetchTodos = async () => {
    try {
      const response = await axiosInstance.get(
        `/api/todos?memberId=${memberId}`,
      );
      setTodos(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    fetchTodos();
  }, []);

  const handleDelete = async (todoId: number) => {
    try {
      await axiosInstance.delete(`/api/todos/${todoId}`);
      setTodos((prevTodos) =>
        prevTodos.filter((todo) => todo.todoId !== todoId),
      );
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="m-auto my-16 flex w-3/4 flex-col gap-4">
      <button
        className="h-14 w-44 self-end rounded-xl bg-customYellow text-2xl font-medium text-white"
        onClick={() => navigate("/add")}
      >
        Add Task
      </button>
      <div className="flex min-h-[768px] flex-col gap-4 rounded-xl bg-customSky p-8">
        {todos.map((todo) => (
          <TodoItem
            key={todo.todoId}
            todo={todo}
            onDelete={() => handleDelete(todo.todoId)}
          />
        ))}
      </div>
    </div>
  );
}
