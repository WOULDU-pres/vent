// src/components/organisms/FlashDealCard.tsx
import { useFlashDealStore } from "../../store/events/flashDeal";
import { FlashDealResponse } from "../../types/events";
import { cn } from "../../utils/cn";

interface Props {
  deal: FlashDealResponse;
  onParticipate: (dealId: number) => Promise<void>;
}

const STATUS_STYLE = {
  ACTIVE: "bg-status-active/10 text-status-active",
  UPCOMING: "bg-status-upcoming/10 text-status-upcoming",
  ENDED: "bg-status-ended/10 text-status-ended",
} as const;

const STATUS_LABEL = {
  ACTIVE: "진행중",
  UPCOMING: "예정",
  ENDED: "종료"
} as const;


export const FlashDealCard = ({ deal, onParticipate }: Props) => {
  const { actions } = useFlashDealStore();
  const discountRate = Math.round(
    (1 - deal.dealPrice / deal.originalPrice) * 100,
  );

  const handleParticipate = async () => {
    try {
      await onParticipate(deal.id);
      await actions.refreshDeals();
    } catch (error) {
      const message = error instanceof Error ? error.message : "알 수 없는 오류";
      alert(`참여에 실패했습니다: ${message}`);
    }
  };
  const isActive = deal.status === "ACTIVE" && deal.remainingQuantity > 0;

  return (
    <div className="bg-surface-0 rounded-xl p-6 shadow-sm transition-shadow hover:shadow-md">
      <div
        className={cn(
          "inline-flex items-center gap-2 rounded-full px-3 py-1 text-sm",
          STATUS_STYLE[deal.status]
        )}
      >
        <div className="h-2 w-2 rounded-full bg-current" />
        {STATUS_LABEL[deal.status]}
      </div>

      <h3 className="text-surface-900 mt-4 text-xl font-semibold">
        {deal.productName}
      </h3>

      <div className="text-surface-600 mt-4 space-y-2">
        <p className="flex justify-between">
          <span>할인율</span>
          <span className="text-status-active font-medium">
            {discountRate}%
          </span>
        </p>
        <p className="flex justify-between">
          <span>할인가</span>
          <span className="font-medium">
            {deal.dealPrice.toLocaleString()}원
          </span>
        </p>
        <p className="flex justify-between">
          <span>잔여 수량</span>
          <span className="font-medium">
            {deal.remainingQuantity}/{deal.quantity}
          </span>
        </p>
      </div>

      <button
        onClick={handleParticipate}
        disabled={!isActive}
        className={cn(
          "mt-6 w-full rounded-lg py-3 font-medium transition-colors",
          isActive
            ? "bg-primary hover:bg-primary/90 text-surface-0"
            : "bg-surface-200 text-surface-600 cursor-not-allowed",
        )}
      >
        {isActive ? "즉시 참여" : "참여 불가"}
      </button>
    </div>
  );
};
