// src/components/organisms/FlashDealForm.tsx
import { useMutation } from "@tanstack/react-query";
import { useNavigate } from "react-router-dom";
import { createFlashDeal } from "../../api/flashDeal";
import { FlashDealCreateRequest } from "../../types/events";
import { useForm } from "react-hook-form";

export const FlashDealForm = () => {
  const navigate = useNavigate();
  const {
    register,
    handleSubmit,
    formState: { errors },
    reset,
  } = useForm<FlashDealCreateRequest>();

  // React Query Mutation
  const { mutate, isPending } = useMutation({
    mutationFn: createFlashDeal,
    onSuccess: () => {
      reset(); // 폼 초기화
      navigate("/flash-deals");
    },
    onError: (error) => {
      alert(`생성 실패: ${error.message}`);
    },
  });

  // 제출 핸들러
  const onSubmit = (data: FlashDealCreateRequest) => {
    const formatDateTime = (datetime: string) => `${datetime}:00+09:00`; // 초 단위 및 타임존 추가

    mutate({
      ...data,
      startTime: new Date().toISOString(),
      endTime: data.endTime ? formatDateTime(data.endTime) : undefined,
    });
  };

  return (
    <form
      onSubmit={handleSubmit(onSubmit)}
      className="mx-auto max-w-2xl space-y-6 rounded-lg bg-white p-8 shadow-md"
    >
      <div>
        <label className="block text-sm font-medium text-gray-700">
          상품명
        </label>
        <input
          {...register("productName", { required: "필수 입력 항목" })}
          className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500"
        />
        {errors.productName && (
          <p className="mt-1 text-sm text-red-600">
            {errors.productName.message}
          </p>
        )}
      </div>

      <div className="grid grid-cols-1 gap-6 md:grid-cols-2">
        <div>
          <label className="block text-sm font-medium text-gray-700">
            원가
          </label>
          <input
            type="number"
            {...register("originalPrice", {
              required: "필수 입력 항목",
              min: { value: 1000, message: "1000원 이상 입력" },
            })}
            className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500"
          />
        </div>

        <div>
          <label className="block text-sm font-medium text-gray-700">
            할인가
          </label>
          <input
            type="number"
            {...register("dealPrice", {
              required: "필수 입력 항목",
              max: 999,
            })}
            className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500"
          />
        </div>

        <div>
          <label className="block text-sm font-medium text-gray-700">
            수량
          </label>
          <input
            type="number"
            {...register("quantity", {
              required: "필수 입력 항목",
              max: 999,
            })}
            className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500"
          />
        </div>

        {/* 시작 시간 입력 필드 */}
        <div>
          <label className="block text-sm font-medium text-gray-700">
            시작 시간
          </label>
          <input
            type="datetime-local"
            {...register("startTime", { required: "필수 입력 항목" })}
            className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500"
          />
        </div>

        {/* 종료 시간 입력 필드 */}
        <div>
          <label className="block text-sm font-medium text-gray-700">
            종료 시간
          </label>
          <input
            type="datetime-local"
            {...register("endTime")}
            className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500"
          />
        </div>
      </div>

      <button
        type="submit"
        disabled={isPending}
        className="w-full rounded-md bg-blue-600 px-4 py-2 text-white transition-colors hover:bg-blue-700"
      >
        딜 생성
      </button>
    </form>
  );
};
