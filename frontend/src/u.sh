#!/bin/bash

# mkdir -p src/components/atoms/Button
# mkdir -p src/components/molecules/SearchBar
# mkdir -p src/components/organisms/charts
# mkdir -p src/components/templates/MainLayout

# mv ./components/layout/PageContainer.tsx ./components/templates/MainLayout/
# mv ./pages/Dashboard.tsx ./pages/Dashboard/index.tsx

mv ./store/flashDealStore.ts ./store/events/flashDeal.ts
touch ./store/globalStore.ts