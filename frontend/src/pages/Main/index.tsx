// src/pages/Main/index.tsx
import { Link } from "react-router-dom";

export const MainPage = () => {
  return (
    <div className="mx-auto max-w-7xl px-4 py-12 sm:px-6 lg:px-8">
      <h1 className="text-surface-900 mb-12 text-center text-4xl font-bold">
        VENT 이벤트 관리 시스템
      </h1>

      <div className="grid grid-cols-1 gap-8 md:grid-cols-2">
        {/* 100원 딜 카드 */}
        <Link
          to="/flash-deals"
          className="group bg-surface-0 rounded-2xl p-8 shadow-lg transition-shadow hover:shadow-xl"
        >
          <div className="flex items-center gap-6">
            <div className="bg-primary/10 flex h-20 w-20 items-center justify-center rounded-xl">
              <span className="text-primary text-3xl font-bold">100</span>
            </div>
            <div>
              <h2 className="text-surface-900 group-hover:text-primary text-2xl font-semibold transition-colors">
                100원 특가 목록
              </h2>
              <p className="text-surface-600 mt-2">
                실시간 특가 상품 관리 및 모니터링
              </p>
            </div>
          </div>
        </Link>

        {/* 100원 딜 카드 */}
        <Link
          to="/flash-deals/create"
          className="group bg-surface-0 rounded-2xl p-8 shadow-lg transition-shadow hover:shadow-xl"
        >
          <div className="flex items-center gap-6">
            <div className="bg-primary/10 flex h-20 w-20 items-center justify-center rounded-xl">
              <span className="text-primary text-3xl font-bold">100</span>
            </div>
            <div>
              <h2 className="text-surface-900 group-hover:text-primary text-2xl font-semibold transition-colors">
                100원 특가 생성하기
              </h2>
              <p className="text-surface-600 mt-2">실시간 특가 상품 생성</p>
            </div>
          </div>
        </Link>

        {/* 대시보드 카드 */}
        <Link
          to="/dashboard"
          className="group bg-surface-0 rounded-2xl p-8 shadow-lg transition-shadow hover:shadow-xl"
        >
          <div className="flex items-center gap-6">
            <div className="bg-secondary/10 flex h-20 w-20 items-center justify-center rounded-xl">
              <span className="text-secondary text-3xl font-bold">📊</span>
            </div>
            <div>
              <h2 className="text-surface-900 group-hover:text-secondary text-2xl font-semibold transition-colors">
                통합 대시보드
              </h2>
              <p className="text-surface-600 mt-2">
                전체 이벤트 현황 종합 분석
              </p>
            </div>
          </div>
        </Link>
      </div>

      <div className="mt-16 text-center">
        <p className="text-surface-500">
          원하는 서비스를 선택하여 관리를 시작하세요
        </p>
      </div>
    </div>
  );
};
