// frontend/src/store/appStore.ts
import { create } from "zustand";

interface AppState {
  selectedEventId: number | null;
  setSelectedEventId: (id: number | null) => void;

  isSidebarOpen: boolean;
  toggleSidebar: () => void;
}

export const useAppStore = create<AppState>((set) => ({
  selectedEventId: null,
  setSelectedEventId: (id) => set(() => ({ selectedEventId: id })),

  isSidebarOpen: false,
  toggleSidebar: () =>
    set((state) => ({ isSidebarOpen: !state.isSidebarOpen })),
}));
