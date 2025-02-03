// src/components/ui/FlashDealCard.tsx
import { FlashDealResponse } from '../../types/flashDeal';
import { useFlashDealStore } from '../../store/flashDealStore';

interface Props {
  deal: FlashDealResponse;
  onParticipate: (dealId: number) => Promise<void>;
}

  export const FlashDealCard = ({ deal, onParticipate }: Props) => {
  const { actions } = useFlashDealStore();

  const handleParticipate = async () => {
    try {
      await onParticipate(deal.id);
      await actions.refreshDeals(); 
    } catch (error) {
      alert('참여에 실패했습니다: ' + (error as Error).message);
    }
  };

  return (
    <div className="deal-card">
      <h3>{deal.productName}</h3>
      <p>딜 가격: {deal.dealPrice}원 (-{Math.round((1 - deal.dealPrice/deal.originalPrice)*100)}%)</p>
      <p>잔여 수량: {deal.remainingQuantity}/{deal.quantity}</p>
      <button 
        onClick={handleParticipate}
        disabled={deal.status !== 'ACTIVE' || deal.remainingQuantity <= 0}
      >
        {deal.status === 'ACTIVE' ? '참여하기' : '종료된 딜'}
      </button>
    </div>
  );
};
