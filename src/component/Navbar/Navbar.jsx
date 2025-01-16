import { Avatar, Badge, Box, IconButton } from '@mui/material'
import React from 'react'
import SearchIcon from '@mui/icons-material/Search';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import ShoppingCartOutlinedIcon from '@mui/icons-material/ShoppingCartOutlined';
import "./Navbar.css"
import { Person } from '@mui/icons-material';
import { useNavigate } from "react-router-dom";
import { useSelector } from 'react-redux';

export const Navbar=()=>{
    const {auth,cart}=useSelector(store=>store)
    const navigate= useNavigate()
    const handleAvatarClick=()=>{
        if(auth.user?.role==="ROLE_CUSTOMER")
        {
            navigate("/my-profile")
        }
        else{
            navigate("/admin/restaurants")
        }
    }

    const cartItemCount = cart.cart?.item.length;
    
    return (
        <Box className='px-5 sticky top-0 z-50 py-[.8rem] bg-[#B42B4D] lg:px-20 flex justify-between'>

        <div className='lg:mr-10 cursor-pointer flex items-center space-x-4'>
            <li onClick={()=>navigate("/")} className="logo font-semibold text-gray-300 text-2xl transition-colors">
                Zosh Food
            </li>

        </div>

        <div className="flex items-center space-x-2 sm:space-x-3 md:space-x-4 lg:space-x-6">
          
            <div className=''>
                <IconButton>
                    <SearchIcon sx={{ fontSize: "1.5rem"}}>

                    </SearchIcon>

                </IconButton>
            </div>

            <div className=''>
              {auth.user ? <Avatar onClick={handleAvatarClick} sx={{bgcolor:"white",fontSize: "1.5rem", color:"#B42B4D", fontWeight:"bold"}}>{auth.user?.fullName[0].toUpperCase()}</Avatar>:
              <IconButton onClick={()=>navigate("/account/login")}>
                <Person/>
              </IconButton> }
             
            </div>

            <div className=''>
            <IconButton onClick={()=>{navigate("/cart");  setTimeout(() => window.location.reload(), 300)}}>
                <Badge 
                    color="secondary"      //  line no 11 const cartItemCount = 3; using change cartitemcount
                    badgeContent={cartItemCount > 0 ? cartItemCount : null}
                >
                    {cartItemCount > 0 ? (
                        <ShoppingCartIcon sx={{ fontSize: "1.5rem" }} />
                    ) : (
                        <ShoppingCartOutlinedIcon sx={{ fontSize: "1.5rem" }} />
                    )}
                </Badge>
            </IconButton>




                {/* <IconButton >
                    <Badge color="secondary" badgeContent={3}> // if you update 3 then go to 11 number line const cartItemCount = 3;
                    <ShoppingCartIcon sx={{ fontSize: "1.5rem"}}>

                     </ShoppingCartIcon>

                    </Badge>
                </IconButton> */}
            </div>


        </div>
     
        </Box>
    )
}