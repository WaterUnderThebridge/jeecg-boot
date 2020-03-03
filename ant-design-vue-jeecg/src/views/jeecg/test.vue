<template>
  <div>
    <div>{{message}}</div>
    <J-ellipsis v-model="msg"/>
  </div>
</template>

<script>
    import JEllipsis from "../../components/jeecg/JEllipsis";
    import { getAction } from '@/api/manage';
    export default {
      name: "message",
      data() {
        return {
          message: "",
          msg: "这里是文本！这里是文本！这里是文本！这里是文本！这里是文本！这里是文本！这里是文本！这里是文本！这里是文本！这里是文本！"
        }
      },
      components: {
        JEllipsis
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