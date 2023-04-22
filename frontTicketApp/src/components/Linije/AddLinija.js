import React, { useState, useEffect, useCallback } from "react";
import { Row, Col, Button, Table, Form } from "react-bootstrap";
import TestAxios from "../../apis/TestAxios";
import { useNavigate } from "react-router-dom";

const AddLinija = (props) => {

    var linija = {
        brojMesta: -1,
        cenaKarte: -1,
        vremePolaska: '',
        destinacija: '',
        prevoznikId: -1,
        prevoznikNaziv: '',
      };
    
      const [addLinija, setAddLinija] = useState(linija);

      const valueInputChanged = (e) => {
        let input = e.target;
        let name = input.name;
        let value = input.value;
    
        let lineFromState = { ...addLinija };
        lineFromState[name] = value;
        setAddLinija(lineFromState);
      };


      const onPrevChange = (e) => {
        const value = e.target.value;
        const selectedPrev = props.prevoznici.find((prev) => prev.id === parseInt(value));
        if (selectedPrev) {
          setAddLinija((prev) => ({
            ...prev,
            prevoznikId: selectedPrev.id,
            prevoznikNaziv: selectedPrev.naziv,
          }));
        }
      };

      const create = (e) => {
        e.preventDefault();

        const dateRegex = /^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1]) (2[0-3]|[01][0-9]):[0-5][0-9]$/;

        if (!dateRegex.test(addLinija.vremePolaska)) {
        alert("Datum i vreme nisu u odgovarajućem formatu. Molimo Vas unesite u formatu yyyy-mm-dd hh:mm.");
         return;
        }
      
         if (
          addLinija.brojMesta < 0 ||
          addLinija.cenaKarte < 0 ||
          !addLinija.destinacija  ||
          !addLinija.vremePolaska ||
          addLinija.prevoznikId === -1
        ) {
          alert("Molimo Vas popunite sva obavezna polja.");
          return;
        }

        var params = {
            brojMesta: addLinija.brojMesta,
            cenaKarte: addLinija.cenaKarte,
            vremePolaska: addLinija.vremePolaska,
            destinacija: addLinija.destinacija,
            prevoznikId: addLinija.prevoznikId,
            prevoznikNaziv: addLinija.prevoznikNaziv,
          };
    
        TestAxios.post('/linije', params)
        .then(res => {
            console.log(res);
            alert('Linija was added successfully!');
            window.location.reload(); 
        })
        .catch(error => {
            console.log(error);
            alert('Error occured please try again!');
         });
    }

    return (
      <>
        <Row>
          <Col xs="12" sm="10" md="8">
            <h1>Dodavanje novih autobuskih linija</h1>

            <Form>
              <Form.Group>
                <Form.Label htmlFor="brojMesta">broj Mesta</Form.Label>
                <Form.Control
                  id="brojMesta"
                  name="brojMesta"
                  type="number"
                  onChange={(e) => valueInputChanged(e)}
                />{" "}
              </Form.Group>

              <Form.Group>
                <Form.Label htmlFor="cenaKarte">cena Karte</Form.Label>
                <Form.Control
                  type="text"
                  id="cenaKarte"
                  name="cenaKarte"
                  onChange={(e) => valueInputChanged(e)}
                />{" "}
              </Form.Group>

              <Form.Group>
                <Form.Label htmlFor="vremePolaska">Vreme polaska</Form.Label>
                <Form.Control
                  type="text"
                  id="vremePolaska"
                  name="vremePolaska"
                  onChange={(e) => valueInputChanged(e)}
                  placeholder="YYYY-MM-DD HH:mm"
                />
              </Form.Group>

              <Form.Group>
                <Form.Label htmlFor="destinacija">destinacija</Form.Label>
                <Form.Control
                  type="text"
                  id="destinacija"
                  name="destinacija"
                  onChange={(e) => valueInputChanged(e)}
                />
              </Form.Group>

              <Row>
                <Col>
                  <Form.Group>
                    <Form.Label>prevoznik</Form.Label>
                    <Form.Select name="id" onChange={(e) => onPrevChange(e)}>
                      <option value="">Izaberi prevoznika</option>
                      {props.prevoznici.map((prev) => {
                        return (
                          <option key={prev.id} value={prev.id}>
                            {prev.naziv}
                          </option>
                        );
                      })}
                    </Form.Select>
                  </Form.Group>
                </Col>
              </Row>
              <br/>
              <Button type="submit" onClick={create}>
                Edit
              </Button>
            </Form>
          </Col>
          <Col></Col>
        </Row>
      </>
    );

}

export default AddLinija;