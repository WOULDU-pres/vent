// src/pages/Dashboard.tsx
import { useQuery } from "@tanstack/react-query";
import { fetchAllEvents } from "../../api/event";
import EventChart from "../../components/organisms/charts/EventChart";
import EventTypeFilter from "../../components/molecules/EventTypeFilter";
import { useEventFilterStore } from "../../store/filterStore";

export default function Dashboard() {
  const { selectedType } = useEventFilterStore();

  // 필터 조건 반영된 쿼리
  const { data, isLoading, isError } = useQuery({
    queryKey: ["events", selectedType], // 쿼리 키에 필터 상태 포함
    queryFn: () => fetchAllEvents(selectedType),
    refetchInterval: 5000,
  });

  if (isLoading) return <div className="p-4 text-gray-500">로딩 중...</div>;
  if (isError)
    return <div className="p-4 text-red-500">데이터 불러오기 실패</div>;

  return (
    <div className="space-y-6">
      <h2 className="text-2xl font-bold text-gray-800">이벤트 현황 대시보드</h2>

      {/* 필터 컴포넌트 추가 */}
      <EventTypeFilter />

      {/* 이벤트 카드 그리드 */}
      <div className="grid grid-cols-1 gap-4 md:grid-cols-2 lg:grid-cols-3">
        {data?.map((event) => (
          <div
            key={event.id}
            className="rounded-lg bg-white p-4 shadow transition-shadow hover:shadow-md"
          >
            <h3 className="mb-2 text-lg font-semibold capitalize">
              {event.type.replace("-", " ")}
            </h3>
            <div className="space-y-1 text-sm">
              <p>🗓 시작: {new Date(event.startTime).toLocaleDateString()}</p>
              <p>⏰ 종료: {new Date(event.endTime).toLocaleDateString()}</p>
              <p className="font-medium">
                👥 참여자: {event.participantCount}명
              </p>
            </div>
          </div>
        ))}
      </div>

      {/* 차트 영역 */}
      <div className="mt-8 rounded-xl bg-white p-6 shadow">
        <h3 className="mb-4 text-xl font-semibold">참여자 추이 분석</h3>
        <EventChart events={data || []} />
      </div>
    </div>
  );
}
