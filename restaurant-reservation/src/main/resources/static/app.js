const $ = (sel)=>document.querySelector(sel);
const show = (el)=>el.classList.remove('hidden');
const hide = (el)=>el.classList.add('hidden');
const toast = (msg)=>{const t=$('#toast');t.textContent=msg;t.style.display='block';setTimeout(()=>t.style.display='none',2500)}

const baseKey='rr_base_url',tokenKey='rr_token',roleKey='rr_role';
const getBase=()=>localStorage.getItem(baseKey)||$('#baseUrl').value;
const setBase=v=>{localStorage.setItem(baseKey,v);$('#baseUrl').value=v;}

function updateAuthUI() {
    const token = localStorage.getItem(tokenKey);
    const role  = localStorage.getItem(roleKey);

    if (token) {
        hide($("#authCard"));      // login paneli gizlət
        show($("#logoutBtn"));     // logout çıxır

        // User hissələri görünməlidir
        show($("#createResvCard"));
        show($("#myResvCard"));

        // Admin isə yalnız ROLE_ADMIN üçün
        if (role === "ROLE_ADMIN") {
            show($("#adminCard"));
            $("#adminBadge").style.display = "inline-block";
        } else {
            hide($("#adminCard"));
            $("#adminBadge").style.display = "none";
        }

        $("#authState").textContent = role ? `User (${role})` : "User";

    } else {
        // Guest görünüşü
        show($("#authCard"));
        hide($("#logoutBtn"));

        hide($("#createResvCard"));
        hide($("#myResvCard"));
        hide($("#adminCard"));

        $("#authState").textContent = "Guest";
    }
}

updateAuthUI();

async function api(path,opts={}){
  const base=getBase(),token=localStorage.getItem(tokenKey);
  const headers={'Content-Type':'application/json',...(opts.headers||{})};
  if(token) headers['Authorization']='Bearer '+token;
  const res=await fetch(base+path,{...opts,headers});
  if(!res.ok) throw new Error(await res.text());
  return res.json();
}

$('#saveBase').onclick=()=>setBase($('#baseUrl').value);

$('#registerBtn').onclick=async()=>{
  try{
    await api('/api/auth/register',{method:'POST',body:JSON.stringify({
      username:$('#authUsername').value,password:$('#authPassword').value})});
    toast('Qeydiyyat uğurlu ✅');
  }catch(e){toast('Xəta: '+e.message);}
};

$('#loginBtn').onclick = async () => {
    try {
        const body = JSON.stringify({
            username: $('#authUsername').value.trim(),
            password: $('#authPassword').value
        });

        const data = await api('/api/auth/login', { method: 'POST', body });

        localStorage.setItem(tokenKey, data.token);
        localStorage.setItem(roleKey, data.role);

        updateAuthUI();
        toast('Giriş uğurlu ✅');
    } catch (e) {
        toast('Giriş alınmadı: ' + e.message, false);
    }
};

$('#logoutBtn').onclick = () => {
    localStorage.removeItem(tokenKey);
    localStorage.removeItem(roleKey);
    updateAuthUI();
    toast('Çıxış edildi');
};

