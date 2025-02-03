// src/pages/FlashDealList.tsx
import { useQuery } from '@tanstack/react-query';
import axios from 'axios';
import { FlashDealResponse } from '../types/flashDeal';

export const FlashDealList = () => {
  // React-Query: 딜 목록 조회
  const { data: deals } = useQuery({
    queryKey: ['flashDeals'],
    queryFn: () => 
      axios.get<FlashDealResponse[]>('/api/flash-deals').then(res => res.data)
  });

  return (
    <div className="deal-grid">
      {deals?.map(deal => (
        <div key={deal.id} className="deal-card">
          <h3>{deal.productName}</h3>
          <p>잔여 수량: {deal.remainingQuantity}</p>
          <button 
            onClick={() => 
              axios.post(`/api/flash-deals/${deal.id}/participate`)
            }
          >
            참여하기
          </button>
        </div>
      ))}
    </div>
  );
};
