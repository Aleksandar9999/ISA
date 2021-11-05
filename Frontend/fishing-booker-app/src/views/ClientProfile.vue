<template>
<div class="profile-view">
<div>
    <button>Account</button>
    <button>Password</button>
</div>
<div class="tab-block">
    <div class="tab">
        <label for="">Name:</label>
        <input type="text"> <br>
        <label for="">Surname:</label>
        <input type="text"> <br>
        <label for="">Email adress:</label>
        <input type="text"> <br>        
    </div>
    <div>
        <label for="">New password</label>
        <input type="password"> <br>
        <label for="">Repeat new password</label>
        <input type="password"> <br>
        <div>
            <p v-if="warningB" class="warning">Passwords are not equal.</p>
        </div>
        <button>Change</button>
    </div>
</div>
</div>
</template>

<script>
 //var warningB= false;
 
 export default {

}
</script>

<style scoped>
.profile-view{
    display: flex;
    justify-content: space-between;
    align-items: center;
}
.tab-block{

}

.tab{

}
</style>