import Login from "./Login"
import Register from "./Register"
import About from "./components/About"
import Accordion from "./components/Accordian"
import Banner from "./components/Banner"
import CaseFlow from "./components/CaseFlow"
import Card from "./components/Category"
import Feed from "./components/Feed"
import Footer from "./components/Footer"
import Footer_last from "./components/Footer_last"
import Nav from "./components/Nav"
import Vision from "./components/Vision"
import { Routes,Route } from "react-router-dom"

function App() {

  return (
    <>
    <div className="overflow-x-hidden">
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
      </Routes>
    <Nav />
    <hr />
    <Banner />
    <About />
    <Vision />
    <Card />
    <Feed />
    <CaseFlow />
    <Accordion />
    <Footer />
    <Footer_last />
  </div>
    </>
  )
}

export default App
