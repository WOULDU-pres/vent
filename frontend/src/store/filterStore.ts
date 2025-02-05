// src/store/filterStore.ts
import { create } from "zustand";

interface EventFilterState {
  selectedType: string | null;
  setSelectedType: (type: string | null) => void;
}

export const useEventFilterStore = create<EventFilterState>((set) => ({
  selectedType: null,
  setSelectedType: (type) => set({ selectedType: type }),
}));
