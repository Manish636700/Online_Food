import { Divider, FormControl, Grid, Grid2, RadioGroup, Typography } from '@mui/material';
import React, { useState, useEffect } from 'react';
import './RestaurantDetails.css';
import LocationOnIcon from '@mui/icons-material/LocationOn';
import CalendarTodayIcon from '@mui/icons-material/CalendarToday';
import FormControlLabel from '@mui/material/FormControlLabel';
import Radio from '@mui/material/Radio';
import MenuCard from './MenuCard';
import { useNavigate, useParams } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { getRestaurantById, getRestaurantsCategory } from '../State/Restaurant/Action';
import { getMenuItemsByRestaurantId } from '../State/Menu/Action';




const foodTypes=[
    {label:"All", value:"all"},
    {label:"Vegetarian Only", value:"vegetarian"},
    {label:"Non-Vegetarian", value:"non_vegetarian"},
    {label:"Seasonal", value:"seasonal"}
]


const menu=[1,1,1,1,1,1]

const RestaurantDetails = () => {
    const [foodType,setFoodType]=useState("all");
    const navigate=useNavigate();
    const dispatch=useDispatch();
    const jwt=localStorage.getItem("jwt");
    const {auth,restaurant,menu}=useSelector(store=>store);
    const restaurantImages =restaurant.restaurant?.images || [];
    const [selectedCategory,setSelectedCategory]=useState("");
    const {id,city} = useParams();


    const handleFilter=(e)=>{
        setFoodType(e.target.value)
        console.log(e.target.value,e.target.name);
    }

    const handleFilterCategory=(e,value)=>{
        setSelectedCategory(value)
        console.log(e.target.value,e.target.name,value);
    }

    useEffect(()=>
    {
        dispatch(getRestaurantById({jwt,restaurantId:id}));
        dispatch(getRestaurantsCategory({jwt,restaurantId:id}));
    },[]);

    useEffect(()=>{
        dispatch(
            getMenuItemsByRestaurantId({
            jwt,
            restaurantId:id,
            vegetarian:foodType==="vegetarian",
            nonveg:foodType==="non_vegetarian",
            seasonal:foodType==="seasonal",
            foodCategory:selectedCategory,
        })
    );
    },[selectedCategory,foodType])


    const [currentImageIndex, setCurrentImageIndex] = useState(0);

    useEffect(() => {
        if (restaurantImages.length > 1) {
            const interval = setInterval(() => {
                setCurrentImageIndex((prevIndex) =>
                    prevIndex === restaurantImages.length - 1 ? 0 : prevIndex + 1
                );
            }, 3000); // Change image every 3 seconds

            return () => clearInterval(interval); // Cleanup on component unmount
        }
    }, [restaurantImages]);

    const handleDotClick = (index) => {
        setCurrentImageIndex(index);
    };

    
  return (
    <div className='px-5 lg:px-20 text-left'>
        <section>
            <h3 className='text-gray-500 py-2 mt-10'>Home/India/Indian Fast Food/3</h3>
            <div>
                <Grid container spacing={2} direction="column" alignItems="center" style={{ position: 'relative' }}>
                    <Grid item xs={12} style={{ position: 'relative', width: '100%' }}>
                        <img
                            className="w-full h-[40vh] object-cover"
                            src={restaurantImages[currentImageIndex]}
                             alt={`restaurant-image-${currentImageIndex}`}
                             style={{ width: '100%', height: '40vh', objectFit: 'cover' }} // Ensure styles are applied inline if needed
                        />
                 </Grid>

                 {/* Dots for manual navigation */}
                 <Grid item xs={12} className="dots-container">
                    {restaurantImages.map((_, index) => (
                        <div
                            key={index}
                            onClick={() => handleDotClick(index)}
                             className={`dot ${currentImageIndex === index ? 'active' : ''}`}
                         ></div>
                     ))}
                 </Grid>
                </Grid>

            </div>
            {/* Origin code  <div>
                <Grid container spacing={2}>
                    <Grid item xs={12}>
                        <img
                         className='w-full h-[40vh] object-cover'
                         src="https://plus.unsplash.com/premium_photo-1661883237884-263e8de8869b?w=1000&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8cmVzdGF1cmFudHxlbnwwfHwwfHx8MA%3D%3D"

                         alt=""
                        />

                    </Grid>

                    <Grid item xs={12} lg={6}>
                        <img
                         className='w-full h-[40vh] object-cover'
                         src="https://plus.unsplash.com/premium_photo-1661883237884-263e8de8869b?w=1000&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8cmVzdGF1cmFudHxlbnwwfHwwfHx8MA%3D%3D"

                         alt=""
                        />

                    </Grid>

                    <Grid item xs={12} lg={6}>
                        <img
                         className='w-full h-[40vh] object-cover'
                         src="https://plus.unsplash.com/premium_photo-1661883237884-263e8de8869b?w=1000&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8cmVzdGF1cmFudHxlbnwwfHwwfHx8MA%3D%3D"

                         alt=""
                        />

                    </Grid>

                </Grid>
            </div> */}

            <div className='pt-3 pb-5'>
                <h1 className="text-4xl font-semibold">{restaurant.restaurant?.name}</h1>
                <p className='text-gray-500 mt-1'>
                 {restaurant.restaurant?.description}
               </p>
                <div className='space-y-3 mt-3'>
                    <p className='text-gray-500 flex items-center gap-3'>
                        <LocationOnIcon></LocationOnIcon>
                            <span>
                            {restaurant.restaurant?.address.street} , {restaurant.restaurant?.address.postalCode}
                            , {restaurant.restaurant?.address.city} , {restaurant.restaurant?.address.state} , 
                            {restaurant.restaurant?.address.country}
                            </span>
                    </p>


                    <p className='text-gray-500 flex items-center gap-3'>
                        <CalendarTodayIcon></CalendarTodayIcon>
                            <span>
                                Mon-Sun: {restaurant.restaurant?.openingHours}
                            </span> 
                    </p>
                </div>
                
            </div>
        </section>

        <Divider/>
        <section className='pt-[2rem] lg:flex relative'>
            <div className='space-y-10 lg:w-[20%] filter '>

                <div className='box space-y-5 lg:sticky top-28 '>
                    <div>
                        <Typography variant='h5' sx={{paddingBottom:"1rem"}}>
                            Food Type
                        </Typography>

                        <FormControl className='py-10 space-y-5' component={"fieldset"}>
                            <RadioGroup onChange={handleFilter} name="food_type" value={foodType}>
                                {foodTypes.map((item)=> (
                                    <FormControlLabel 
                                    key={item.value}
                                    value={item.value} control={<Radio />} label={item.label} />))}

                            </RadioGroup>
                        </FormControl>
                    </div>
                    <Divider/>
                    <div>
                        <Typography variant='h5' sx={{paddingBottom:"1rem"}}>
                            Food Category
                        </Typography>

                        <FormControl className='py-10 space-y-5' component={"fieldset"}>
                            <RadioGroup onChange={handleFilterCategory} name="food_category"
                            
                            value={selectedCategory}
                            
                            >
                                {restaurant.categories.map((item)=> (
                                    <FormControlLabel 
                                    key={item}
                                    value={item.name} control={<Radio />} label={item.name} />))}

                            </RadioGroup>
                        </FormControl>
                    </div>
                </div>
            </div>

            <div className='space-y-5 lg:w-[80%] lg:pl-10'>
                {menu.menuItems.map((item)=><MenuCard item={item}/>)}
            </div>
        </section>

        
    </div>
  )
}

export default RestaurantDetails