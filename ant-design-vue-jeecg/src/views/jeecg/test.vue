<template>
    <div>{{message}}</div>
</template>

<script>
    import { getAction } from '@/api/manage';
    export default {
      name: "message",
      data() {
        return {
          message: ""
        }
      },
      methods: {
        name() {

        }
      },
      created() {
        getAction("/test/jeecgTest/hello").then(res=>{
          console.log("...")
          this.message=res.result;
        })
      }
    }
</script>

<style scoped>

</style>