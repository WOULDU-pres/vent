// src/pages/FlashDeal/List.tsx 
import { useQuery } from '@tanstack/react-query';
import { fetchFlashDeals, participateFlashDeal } from '../../api/flashDeal';
import { FlashDealCard } from '../../components/ui/FlashDealCard';

export const FlashDealListPage = () => {
  const { data: deals, isLoading } = useQuery({
    queryKey: ['flashDeals'],
    queryFn: fetchFlashDeals 
  });

  if (isLoading) return <div className="p-4 text-gray-500">로딩 중...</div>;

  const handleParticipate = async (dealId: number) => {
    try {
      await participateFlashDeal(dealId);
    } catch (error) {
      throw new Error('참여 처리 실패: ' + (error as Error).message);
    }
  };

  return (
    <div className="grid-container">
      {deals?.map((deal) => (
        <FlashDealCard 
          key={deal.id}
          deal={deal}
          onParticipate={handleParticipate} 
        />
      ))}
    </div>
  );
};
