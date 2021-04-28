<template>
  <div class="home">
    <el-container>
      <el-header id="home-header">
        <span class="title">MSI Bistro</span>
        <el-menu router mode="horizontal">
          <el-menu-item index="/new-order">New Order</el-menu-item>
          <el-menu-item index="/pick-up">Pick Up</el-menu-item>
          <el-menu-item index="/all-orders">All Orders</el-menu-item>
        </el-menu>
      </el-header>

      <el-main>
        <div class="homeWelcome" v-if="this.$router.currentRoute.path==='/'">
          Welcome to One Night Restaurant!
        </div>
        <router-view class="homeRouterView"/>
      </el-main>
    </el-container>

  </div>
</template>

<script>
export default {
  name: 'Home'
}
</script>

<style>
.homeWelcome {
  text-align: center;
  font-size: 30px;
  color: #3FABD6;
  padding-top: 3rem;
}

#home-header {
  background-color: #fff;
  display: flex;
  border-bottom: solid 1px #e6e6e6;
}

#home-header .title {
  font-size: 1.5rem;
  align-self: center;
}
</style>