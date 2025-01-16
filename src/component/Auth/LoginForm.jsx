import { Password } from '@mui/icons-material'
import { Button, TextField, Typography } from '@mui/material'
import { Field, Form, Formik } from 'formik'
import React from 'react'
import { useDispatch } from 'react-redux'
import { useNavigate } from 'react-router-dom'
import { loginUser } from '../State/Authentication/Action'




const initialValues={
    email:"",
    password:""
}

export const LoginForm = () => {

const navigate=useNavigate()
const dispatch=useDispatch()

    const handleSubmit=(Values)=>{
        dispatch(loginUser({userData:Values,navigate}))
    }
  return (
    <div>
        <Typography variant='h5' className='text-center'>
            Login
        </Typography>

        <Formik onSubmit={handleSubmit} initialValues={initialValues}>
            <Form>
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

                <Button sx={{mt:2,padding:"1rem"}} fullWidth type='submit' variant='contained' >Login</Button>  
            </Form>
        </Formik>

        <Typography variant='body2' align='center' sx={{mt:3}}>
            Don't Have an Account?
            <Button size='small' onClick={()=>navigate("/account/register")}>
                register
            </Button>
        </Typography>
    </div>
  )
}
