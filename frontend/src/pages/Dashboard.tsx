// frontend/src/pages/Dashboard.tsx
import { useQuery } from '@tanstack/react-query';
import { fetchAllEvents, EventResponse } from '../api/event';
import EventChart from '../components/EventChart';

export default function Dashboard() {
  const { data, isLoading, isError } = useQuery<EventResponse[]>({
    queryKey: ['allEvents'],
    queryFn: fetchAllEvents,
    refetchInterval: 5000, // 5초마다 데이터 갱신
  });

  if (isLoading) return <div>Loading...</div>;
  if (isError) return <div>Error!</div>;

  return (
    <div>
      <h2 className="text-2xl font-bold">이벤트 현황</h2>

      {/* 이벤트 카드 목록 */}
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
        {data?.map((event) => (
          <div key={event.id} className="border rounded p-3 bg-white">
            <h3 className="text-xl font-semibold">{event.type}</h3>
            <p>시작: {new Date(event.startTime).toLocaleString()}</p>
            <p>종료: {new Date(event.endTime).toLocaleString()}</p>
            {event.participantCount !== undefined && (
              <p>참여자 수: {event.participantCount}</p>
            )}
          </div>
        ))}
      </div>



      {/* 간단한 차트 - 참여자 통계 예시 */}
      <div className="p-4 bg-white rounded shadow">
        <h3 className="text-xl font-semibold mb-2">이벤트별 참여자 차트</h3>
        <EventChart events={data || []} />
      </div>
    </div>
  );
}
