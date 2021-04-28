<template>
  <div>
    <el-form :inline="true" :model="searchForm">
      <el-form-item label="Enter Phone:">
        <el-input v-model="searchForm.phone" placeholder="Customer's Phone"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="searchPendingOrder">Search</el-button>
      </el-form-item>
    </el-form>

    <p v-if="!order" style="text-align: center; color: #999">
      {{ emptyMessage }}
    </p>
    <el-row>
      <el-col :md="12">
        <el-card v-if="order">
          <div slot="header">
            <span style="color: #999">Customer: </span>
            {{ this.order.customer.name }}
            <el-divider direction="vertical"></el-divider>
            <span style="color: #999">Phone: </span>
            {{ this.order.customer.phone }}
          </div>
          <p v-for="orderFoods in this.order.foodsList" :key="orderFoods.id">
            {{ orderFoods.food.name + " × " + orderFoods.number }}
          </p>
          <el-divider></el-divider>
          <p>
            {{ "Total: $" + this.order.orderPrice }}
          </p>
          <el-button type="primary" style="width: 100%" @click="pickUpOrder">Pick Up</el-button>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "PickUp",
  data() {
    return {
      searchForm: { phone: "" },
      order: null,
      emptyMessage: "Enter customer's phone number and search."
    }
  },
  methods: {
    searchPendingOrder() {
      if (this.searchForm.phone.length === 0)
        return this.$message.error({ message: "Please enter customer's phone number." });
      this.axios.get(`/orders?phone=${this.searchForm.phone}&status=0`).then(resp => {
        // console.log(resp);
        this.order = resp[0];
        this.emptyMessage = "No result.";
      });
    },
    pickUpOrder() {
      this.axios.put(`/orders/pickup/${this.order.id}`).then(() => {
        this.order = null;
        this.emptyMessage = "Enter customer's phone number and search.";
      });
    }
  }
}
</script>

<style scoped>

</style>