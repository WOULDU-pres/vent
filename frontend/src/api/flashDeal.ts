// src/api/flashDeal.ts
import { apiClient } from './instance';
import { FlashDealResponse, FlashDealCreateRequest } from '../types/events';

export const fetchFlashDeals = async () => {
  const response = await apiClient.get<FlashDealResponse[]>('/flash-deals');
  return response.data;
};

export const createFlashDeal = async (data: FlashDealCreateRequest) => {
  const response = await apiClient.post<FlashDealResponse>('/flash-deals', data);
  return response.data;
};

export const participateFlashDeal = async (dealId: number) => {
  const response = await apiClient.post<FlashDealResponse>(
    `/flash-deals/${dealId}/participate`
  );
  return response.data;
};
