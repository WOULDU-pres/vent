// src/App.tsx 
import { QueryClient, QueryClientProvider } from '@tanstack/react-query'
import { ReactQueryDevtools } from '@tanstack/react-query-devtools'
import { Router } from './routes/Router'

// React Query 전역 설정
const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      staleTime: 5 * 60 * 1000, // 5분간 fresh 상태 유지
      retry: 1, // 실패 시 1회 재시도
      refetchOnWindowFocus: false // 창 포커스 시 자동 refetch 비활성화
    }
  }
})

export const App = () => {
  return (
    <QueryClientProvider client={queryClient}>
      <Router />
      <ReactQueryDevtools initialIsOpen={false} position="bottom" />
    </QueryClientProvider>
  )
}
