import React, { useEffect, useRef, useState } from 'react'
import { ProfileNavigation } from './ProfileNavigation'
import { Route, Routes } from 'react-router-dom';
import UserProfile from './UserProfile';
import { Orders } from './Orders';
import { Address } from './Address';
import { Favorites } from './Favorites';
import { Events } from './Events';

const Profile = () => {
    const[openSideBar,setOpenSideBar] = useState(false);
    const sidebarRef = useRef(null);

    const toggleSidebar = () => {
      setOpenSideBar(!openSideBar);
  };
  const handleClickOutside = (event) => {
    if (sidebarRef.current && !sidebarRef.current.contains(event.target)) {
        setOpenSideBar(false);
    }
};


useEffect(() => {
  if (openSideBar) {
      document.addEventListener('mousedown', handleClickOutside);
  } else {
      document.removeEventListener('mousedown', handleClickOutside);
  }

  return () => {
      document.removeEventListener('mousedown', handleClickOutside);
  };
}, [openSideBar]);

  return (
    <div className='lg:flex justify-between'>
        <div
                ref={sidebarRef}
                className=''>
            <ProfileNavigation open={openSideBar} closeSidebar={() => setOpenSideBar(false)} />
        </div>

        <div className='lg:w-[80%]'>
        <Routes>
            <Route path='/' element={<UserProfile/>}/>
            <Route path='/orders' element={<Orders/>}/>
            <Route path='/address' element={<Address/>}/>
            <Route path='/favorites' element={<Favorites/>}/>
            <Route path='/events' element={<Events/>}/>

        </Routes>
        </div>
        <button
                onClick={toggleSidebar}
                className="fixed bottom-5 right-5 lg:hidden p-3 bg-blue-600 text-white rounded-full shadow-lg z-50"
            >
                {openSideBar ? 'Close' : 'Menu'}
            </button>
    </div>
  );
};

export default Profile
