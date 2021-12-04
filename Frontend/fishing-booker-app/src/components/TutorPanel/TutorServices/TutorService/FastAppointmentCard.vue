<template lang="">
    <w-card shadow style="margin-top:15px">
      <w-flex wrap class="text-center">
        <div class="xs6 pa1">
          <div class="py3">
              <b>Period: {{periodAppointmentStart}} -  {{periodAppointmentEnd}} </b> <br/> 
              <span class="action-label">Akcija vazi: {{periodFastStart}} - {{periodFastEnd}}</span>
         </div>
        </div>
        <div class="xs6 pa1">
          <div class="py3"><w-button class="ma1" bg-color="success">Rezervisi</w-button></div>
        </div>
      </w-flex>
    </w-card>
</template>

<script>
//TODO: Povezati sa proslijedjenom rezervacijom
export default {
    props:['fast_appoinement'],
    data(){
        return{
            periodAppointmentStart:'01.02.2021',
            periodAppointmentEnd:'01.02.2021',
            periodFastStart:'01.02.2021',
            periodFastEnd:'01.02.2021',
        }
    }
}
</script>
<style>
.action-label{
    color: red;
    font-size: 13px;
}
</style>