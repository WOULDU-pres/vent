// src/pages/FlashDeal/Create.tsx
import { FlashDealForm } from "../../components/organisms/FlashDealForm";

export const FlashDealCreatePage = () => {
  return (
    <div className="create-page">
      <h1>새 100원 딜 생성</h1>
      <FlashDealForm />
    </div>
  );
};
