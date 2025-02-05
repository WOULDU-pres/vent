// src/types/flashDeal.ts
export interface FlashDealResponse {
    id: number;
    productName: string;
    originalPrice: number;
    dealPrice: number;
    quantity: number;
    remainingQuantity: number;
    startTime: string;
    endTime: string;
    status: 'UPCOMING' | 'ACTIVE' | 'ENDED';
  }
  
export interface FlashDealCreateRequest {
  productName: string;
  originalPrice: number;
  dealPrice: number;
  quantity: number;
  startTime: string;
  endTime?: string   // [옵션] 서버에서 자동 계산 가능
}
  