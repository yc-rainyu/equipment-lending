<script setup></script>

<template>
  <header>
    <p>資訊設備借用管理系統</p>
    <nav>
      <RouterLink to="/equipment">設備</RouterLink>
      <RouterLink to="/loans">借用歷史</RouterLink>
    </nav>
  </header>
  <main>
    <RouterView />
  </main>
</template>

<style scoped></style>
