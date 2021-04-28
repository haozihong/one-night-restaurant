<template>
  <div>
    <el-form :inline="true" :model="searchForm">
      <el-form-item label="Search Orders:">
        <el-input v-model="searchForm.keyword" placeholder="Keyword"></el-input>
      </el-form-item>
      <el-form-item label="By:">
        <el-select v-model="searchForm.searchBy" placeholder="Search By">
          <el-option label="Order ID" value="orderId"></el-option>
          <el-option label="Customer ID" value="customerId"></el-option>
          <el-option label="Customer Name" value="customerName"></el-option>
          <el-option label="Phone" value="phone"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button @click="searchCustomers">Search</el-button>
      </el-form-item>
    </el-form>

    <el-form>
      <el-form-item label="Filter:">
        <el-checkbox v-model="includePending">PENDING</el-checkbox>
        <el-checkbox v-model="includePickedUp">PICKED_UP</el-checkbox>
      </el-form-item>
    </el-form>

    <el-card>
      <el-table :data="ordersFiltered" :empty-text="tableEmptyText" stripe>
        <el-table-column prop="id" label="ID" width="50"/>
        <el-table-column label="Time">
          <template slot-scope="scope">
            {{ scope.row.createTime | dateFormat }}
          </template>
        </el-table-column>
        <el-table-column label="Customer">
          <template slot-scope="scope">
            {{ scope.row.customer.name }}
          </template>
        </el-table-column>
        <el-table-column label="Phone">
          <template slot-scope="scope">
            {{ scope.row.customer.phone }}
          </template>
        </el-table-column>
        <el-table-column prop="orderPrice" label="Total Price ($)"/>
        <el-table-column label="Status">
          <template slot-scope="scope">
            {{ scope.row.status === 0 ? "PENDING" : "PICKED_UP" }}
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "AllOrders",
  data() {
    return {
      searchForm: {
        keyword: '',
        searchBy: ''
      },
      orders: [],
      ordersFiltered: [],
      tableEmptyText: "No Data",
      includePending: true,
      includePickedUp: true
    }
  },
  watch: {
    includePending() {
      this.filterOrders();
    },
    includePickedUp() {
      this.filterOrders();
    }
  },
  methods: {
    searchCustomers() {
      if (this.searchForm.keyword.length === 0 || this.searchForm.searchBy.length === 0)
        return this.$message.error({ message: "Please complete all fields." });
      let param = "";
      switch (this.searchForm.searchBy) {
        case "orderId":
          param = `/${this.searchForm.keyword}`;
          break;
        case "customerId":
          param = `?customerId=${this.searchForm.keyword}`;
          break;
        case "customerName":
          param = `?customerName=${this.searchForm.keyword}`;
          break;
        case "phone":
          param = `?phone=${this.searchForm.keyword}`;
      }
      this.tableEmptyText = "Fetching Data...";
      this.axios.get(`/orders${param}`).then(resp => {
        // console.log(resp);
        if (resp) {
          this.orders = this.searchForm.searchBy === "orderId" ? [resp] : resp;
          this.orders.sort((a, b) => a.id - b.id);
          this.filterOrders();
        } else {
          this.orders = this.ordersFiltered = [];
        }
        this.tableEmptyText = "No Data";
      });
    },
    filterOrders() {
      this.ordersFiltered = this.orders.filter(e =>
          (this.includePending && e.status === 0) || (this.includePickedUp && e.status === 1)
      );
    }
  }
}
</script>

<style scoped>

</style>