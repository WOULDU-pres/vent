// src/store/events/flashDeal.ts
import { create } from 'zustand';
import { fetchFlashDeals } from '../../api/flashDeal';
import { FlashDealResponse } from '../../types/events';

interface FlashDealState {
  deals: FlashDealResponse[];
  selectedDeal: FlashDealResponse | null;
  actions: {
    setSelectedDeal: (deal: FlashDealResponse | null) => void;
    refreshDeals: () => Promise<void>;
  };
}

export const useFlashDealStore = create<FlashDealState>((set) => ({
  deals: [],
  selectedDeal: null,
  actions: {
    setSelectedDeal: (deal) => set({ selectedDeal: deal }),
    refreshDeals: async () => {
      const data = await fetchFlashDeals(); 
      set({ deals: data });
    }
  }
}));
