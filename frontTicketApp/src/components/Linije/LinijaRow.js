import { useNavigate } from "react-router-dom";
import TestAxios from "../../apis/TestAxios";
import { useState, useEffect } from "react";
import { Row, Col, Button, Table, Form } from "react-bootstrap";

const LinijaRow = (props) => {

    var navigate = useNavigate()
    var linijaId = props.linija.id;
    const [kolicina, setKolicina] = useState('');

    const valueKolicinaChange = (e) => {
        setKolicina(e.target.value);
    }

    const deleteLinija = (linijaId) => {
        TestAxios.delete('/linije/' + linijaId)
        .then(res => {
            console.log(res);
            alert('Linija was deleted successfully!');
            window.location.reload();
        })
        .catch(error => {
            console.log(error);
            alert('Error occured please try again!');
         });
    }

    const goToEdit = (linijaId) => {
        navigate('/linije/edit/' + linijaId); 
    }

    const rezervacija = (id) => {
      
         if (
          kolicina === ''
        ) {
          alert("Molimo Vas unesite kolicinu.");
          return;
        }

        if (
            kolicina < 0
          ) {
            alert("Molimo Vas unesite pozitivnu kolicinu.");
            return;
          }

        if (
            kolicina > props.linija.brojMesta
          ) {
            alert("Nema dovoljno mesta.");
            return;
          }

        var params = {
            brojKarata: kolicina,
            linijaId: linijaId,
          };
    
        TestAxios.post('/rezervacije', params)
        .then(res => {
            console.log(res);
            alert('rezervacija was added successfully!');
            window.location.reload(); 
        })
        .catch(error => {
            console.log(error);
            alert('Error occured please try again!');
         });
    }

    return (
        <tr key={props.linija.id}>
            <td>{props.linija.prevoznikNaziv}</td>
            <td>{props.linija.destinacija}</td>
            <td>{props.linija.brojMesta}</td>
            <td>{props.linija.vremePolaska}</td>
            <td>{props.linija.cenaKarte}</td>
            
            {window.localStorage.getItem("role")=="ROLE_ADMIN"?
            [<td><Button className="button button-navy" onClick={() => deleteLinija(props.linija.id)} style={{ backgroundColor: "red" }} >Delete</Button></td>,
            <td><Button className="button button-navy" onClick={() => goToEdit(props.linija.id)}  style={{ backgroundColor: "yellow", color: "black" }} >Edit</Button></td>]:null}     


                        <Form.Group>
                            <Form.Control
                            type="number"
                            id="kolicina"
                            name="kolicina"
                            onChange={(e) => valueKolicinaChange(e)}
                            style={{ width: "70px" }}
                            />
                        </Form.Group>,
                        <td>
                            <Button
                            className="button button-navy"
                            onClick={() => rezervacija(linijaId)}
                            >
                            Kupi
                            </Button>
                        </td>

        </tr>
    )



}

export default LinijaRow;