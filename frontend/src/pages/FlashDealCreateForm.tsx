// src/pages/FlashDealCreateForm.tsx
import { useMutation, useQueryClient } from '@tanstack/react-query';
import axios from 'axios';
import { useForm } from 'react-hook-form';

interface FlashDealCreateRequest {
  productName: string;
  originalPrice: number;
  dealPrice: number;
  quantity: number;
  startTime: string;
}

export const FlashDealCreateForm = () => {
  const queryClient = useQueryClient();
  const { register, handleSubmit } = useForm<FlashDealCreateRequest>();
  
  // React-Query Mutation: 새 딜 생성
  const createMutation = useMutation({
    mutationFn: (newDeal: FlashDealCreateRequest) => 
      axios.post('/api/flash-deals', newDeal),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['flashDeals'] })
    }
  });

  return (
    <form onSubmit={handleSubmit(data => createMutation.mutate(data))}>
      <input 
        {...register('productName', { required: true })}
        placeholder="상품명"
      />
      <input
        type="number"
        {...register('originalPrice', { min: 1000 })}
        placeholder="원가"
      />
      <input
        type="number"
        {...register('dealPrice', { max: 999 })}
        placeholder="딜 가격"
      />
      <input
        type="number"
        {...register('quantity', { min: 1 })}
        placeholder="수량"
      />
      <input
        type="datetime-local"
        {...register('startTime', { required: true })}
      />
      <button type="submit">딜 생성</button>
    </form>
  );
};
