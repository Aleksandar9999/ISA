<template>
  <tr>
    <td>{{ item.id }}</td>
    <td>{{ item.name }}</td>
    <td>{{ item.maxPerson }}</td>
    <td>{{ item.rules }}</td>
    <td>
      <router-link :to="{ name: profileName, params: { id: item.id } }"
        >Page</router-link
      >
    </td>
  </tr>
</template>
<script>
export default {
  props: ["item"],
  data() {
    return {
      item_local: {},
    };
  },
  watch: {
    item: {
      immediate: true,
      handler(itemFromProps) {
        if (itemFromProps) {
          this.item_local = {
            ...this.item_local,
            ...itemFromProps,
          };
          this.status = itemFromProps.status;
        }
      },
    },
  },
};
</script>
<style></style>
