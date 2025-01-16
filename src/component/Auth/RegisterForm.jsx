import { Button, FormControl, InputLabel, MenuItem, Select, TextField, Typography } from '@mui/material'
import { Field, Form, Formik } from 'formik'
import React from 'react'
import { useNavigate } from 'react-router-dom'
import { registerUser } from '../State/Authentication/Action'
import { useDispatch } from 'react-redux'



const initialValues={
    fullName:"",
    email:"",
    password:"",
    role:""
}


export const RegisterForm = () => {

    const navigate=useNavigate()
    const dispatch=useDispatch()

    const handleSubmit=(Values)=>{
        console.log("form values", Values);
        dispatch(registerUser({userData:Values,navigate}))

    }
  return (
    <div>
    <Typography variant='h5' className='text-center'>
        Register 
    </Typography>

    <Formik onSubmit={handleSubmit} initialValues={initialValues}>
        <Form>
        <Field as={TextField}
            name="fullName"
            label="full Name"
            fullWidth
            variant="outlined"
            margin="normal"/>


            <Field as={TextField}
            name="email"
            label="email"
            fullWidth
            variant="outlined"
            type="email"
            margin="normal"/>

            <Field as={TextField}
            name="password"
            label="password"
            fullWidth
            variant="outlined"
             type="password"
            margin="normal"/>  

        <FormControl margin="normal" fullWidth>
        <InputLabel id="role-simple-select-label">Role</InputLabel>
        <Field
         as={Select}
            labelId="role-simple-select-label"
            id="demo-simple-select"
            name="role"
            // value={age}
            label="Role"
            // onChange={handleChange}
        >
            <MenuItem value={"ROLE_CUSTOMER"}>Customer</MenuItem>
            <MenuItem value={"ROLE_RESTAURANT_OWNER"}>Restaurant Owner</MenuItem>
        </Field>
        </FormControl>


            <Button sx={{mt:2,padding:"1rem"}} fullWidth type='submit' variant='contained' >Register</Button>  
        </Form>
    </Formik>

    <Typography variant='body2' align='center' sx={{mt:3}}>
        If Have an Account Already?
        <Button size='small' onClick={()=>navigate("/account/login")}>
            Login
        </Button>
    </Typography>
</div>
  )
}
