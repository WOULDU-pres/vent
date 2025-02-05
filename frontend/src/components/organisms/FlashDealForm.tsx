// src/components/organisms/FlashDealForm.tsx
import { useMutation } from '@tanstack/react-query'
import { useNavigate } from 'react-router-dom'
import { createFlashDeal } from '../../api/flashDeal'
import { FlashDealCreateRequest } from '../../types/events'
import { useForm } from 'react-hook-form'

export const FlashDealForm = () => {
  const navigate = useNavigate()
  const { register, handleSubmit, formState: { errors }, reset } = useForm<FlashDealCreateRequest>()

  // React Query Mutation
  const { mutate, isPending } = useMutation({
    mutationFn: createFlashDeal,
    onSuccess: () => {
      reset() // 폼 초기화
      navigate('/flash-deals')
    },
    onError: (error) => {
      alert(`생성 실패: ${error.message}`)
    }
  })

  // 제출 핸들러
  const onSubmit = (data: FlashDealCreateRequest) => {
    mutate({
      ...data,
      startTime: new Date().toISOString()
    })
  }
  
  return (
    <form 
      onSubmit={handleSubmit(onSubmit)} // [수정] 핸들러 연결
      className="space-y-6 max-w-2xl mx-auto bg-white p-8 rounded-lg shadow-md">
      <div>
        <label className="block text-sm font-medium text-gray-700">상품명</label>
        <input
          {...register('productName', { required: '필수 입력 항목' })}
          className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500"
        />
        {errors.productName && (
          <p className="mt-1 text-sm text-red-600">{errors.productName.message}</p>
        )}
      </div>

      <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
        <div>
          <label className="block text-sm font-medium text-gray-700">원가</label>
          <input
            type="number"
            {...register('originalPrice', { 
              required: '필수 입력 항목',
              min: { value: 1000, message: '1000원 이상 입력' }
            })}
            className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500"
          />
        </div>

        <div>
          <label className="block text-sm font-medium text-gray-700">할인가</label>
          <input
            type="number"
            {...register('dealPrice', { 
              required: '필수 입력 항목',
              max: 999
            })}
            className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500"
          />
        </div>
      </div>

      <button
        type="submit"
        disabled={isPending} // [추가] 로딩 상태 비활성화
        className="w-full bg-blue-600 text-white py-2 px-4 rounded-md hover:bg-blue-700 transition-colors"
      >
        딜 생성
      </button>
    </form>
  )
}
