// src/routes/Router.tsx (신규 생성)
import { BrowserRouter, Route, Routes } from "react-router-dom";
import { MainLayout } from "../components/templates/MainLayout";
import { MainPage } from "../pages/Main";
import { FlashDealListPage } from "../pages/FlashDeal/List";
import { FlashDealCreatePage } from "../pages/FlashDeal/Create";
import Dashboard from "../pages/Dashboard";

export const Router = () => (
  <BrowserRouter>
    <Routes>
      <Route
        path="/"
        element={
          <MainLayout>
            <MainPage />
          </MainLayout>
        }
      />
      <Route
        path="/dashboard"
        element={
          <MainLayout>
            <Dashboard />
          </MainLayout>
        }
      />
      <Route
        path="/flash-deals"
        element={
          <MainLayout>
            <FlashDealListPage />
          </MainLayout>
        }
      />
      <Route
        path="/flash-deals/create"
        element={
          <MainLayout>
            <FlashDealCreatePage />
          </MainLayout>
        }
      />
    </Routes>
  </BrowserRouter>
);
