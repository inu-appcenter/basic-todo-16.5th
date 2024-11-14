import { create } from "zustand";

interface UserState {
  email: string | null;
  memberId: string | null;
  setUser: (userData: { email: string; memberId: string }) => void;
  clearUser: () => void;
}

const useUserStore = create<UserState>((set) => ({
  email: null,
  memberId: null,
  setUser: (userData) =>
    set({ email: userData.email, memberId: userData.memberId }),
  clearUser: () => set({ email: null, memberId: null }),
}));

export default useUserStore;
