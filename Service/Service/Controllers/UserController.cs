using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Service.Models;

namespace Service.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class UserController : ControllerBase
    {
        UserContext k = new UserContext();
              
        [HttpGet("read")]
        public List<User> Read()
        { 
            return k.User.ToList();
        }

        [HttpPost("save")]
       public void Save(User u)
        {
            k.Add(u);
             k.SaveChanges();
        }

    }
}
