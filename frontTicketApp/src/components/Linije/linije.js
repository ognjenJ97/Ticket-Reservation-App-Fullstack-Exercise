import React, { useState, useEffect, useCallback } from "react";
import { Row, Col, Button, Table, Form } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import TestAxios from "../../apis/TestAxios";
import AddLinija from "./AddLinija";
import LinijaRow from "./LinijaRow";


const Linije = () => {
    const empty_search = {
        prevoznikPretraga: "",
        desetinacijaPretraga: "",
        maksCenaPretraga: ''
      };

      const [search, setSearch] = useState(empty_search);
      const [totalPages, setTotalPages] = useState(1);
      const [pageNo, setPageNo] = useState(0);
      const [linije, setLinije] = useState([]);
      const [prevoznici, setPrevoznici] = useState([]);
      var navigate = useNavigate();

      const getLinije = (newPageNo) => {
        const conf = {
          params: {
            prevoznikId: search.prevoznikPretraga,
            destinacija: search.desetinacijaPretraga,
            maksCena: search.maksCenaPretraga,
            pageNo: newPageNo,
          },
        };
    
        TestAxios.get("/linije", conf)
          .then((result) => {
            console.log(result);
            setPageNo(newPageNo);
            setTotalPages(result.headers["total-pages"]);
            console.log(totalPages);
            setLinije(result.data);
          })
          .catch((error) => {
            console.log(error);
            alert("Error occured please try again!");
          });
      };

      const getPrevoznici = () => {
        TestAxios.get("/prevoznici")
          .then((res) => {
            console.log(res);
            setPrevoznici(res.data);
          })
          .catch((error) => {
            console.log(error);
            alert("Error occured please try again!");
          });
      };

      useEffect(() => {
        getLinije(0);
        getPrevoznici();
      }, []);

      const onInputChange = (event) => {
        const { name, value } = event.target;
    
        setSearch((prevSearch) => ({
          ...prevSearch,
          [name]: value,
        }));
      };

      const renderZadatak = () => {
        return linije.map((linija) => {
          return (
            <LinijaRow
              key={linija.id}
              linija={linija}
            ></LinijaRow>
          );
        });
      };

      const renderSearchForm = () => {
        return (
          <>
          
            <Row>
              <Col>
                <Form.Group>
                  <Form.Label>Destinacija</Form.Label>
                  <Form.Control
                    name="desetinacijaPretraga"
                    as="input"
                    type="text"
                    onChange={(e) => onInputChange(e)}
                    placeholder="Unesite destinaciju"
                  ></Form.Control>
                </Form.Group>
              </Col>
              </Row>
              <Row>
              <Col>
                <Form.Group>
                  <Form.Label>Maksimalna cena karte</Form.Label>
                  <Form.Control
                    name="maksCenaPretraga"
                    as="input"
                    type="number"
                    onChange={(e) => onInputChange(e)}
                    placeholder="Unesite maksimalnu cenu"
                  ></Form.Control>
                </Form.Group>
              </Col>
              </Row>
              <Row>
              <Col>
                <Form.Group>
                  <Form.Label>Prevoznik</Form.Label>
                  <Form.Select name="prevoznikPretraga" onChange={onInputChange}>
                    <option value="">Izaberi prevoznika</option>
                    {prevoznici.map((prev) => {
                      return (
                        <option value={prev.id} key={prev.id}>
                          {prev.naziv}
                        </option>
                      );
                    })}
                  </Form.Select>
                </Form.Group>
              </Col>
              </Row>
              <Row>
              <Col>
                <Button  className="mt-3" onClick={() => getLinije(0)}>
                  Search
                </Button>
              </Col>
            </Row>
          </>
        );
      };

      return (
        <div>
             {window.localStorage.getItem("role")=="ROLE_ADMIN" && <AddLinija prevoznici={prevoznici}></AddLinija>}   

            
          <Col>
            <Row>
              <h1>Prikaz svih Linija</h1>
            </Row>
            
            <Row>{renderSearchForm()}
            
            <div style={{ display: 'flex', justifyContent: 'flex-end' }}>
            <Button
              disabled={pageNo === 0}
              onClick={() => getLinije(pageNo - 1)}
              className="mr-3"
            >
              Prev
            </Button>
            <Button
              disabled={pageNo === totalPages - 1}
              onClick={() => getLinije(pageNo + 1)}
            >
              Next
            </Button>
            </div>
            </Row>
            <br />
            <Row>
              <Col>
                <Table id="vina-table">
                  <thead style={{ backgroundColor: "black", color: "white" }}>
                    <tr>
                      <th>Naziv prevoznika</th>
                      <th>Destinacija </th>
                      <th>broj mesta</th>
                      <th>Vreme polaska</th>
                      <th>Cena karte (rsd)</th>
                      <th></th>
                      <th></th>
                      <th></th>
                      <th></th>
                    </tr>
                  </thead>
                  <tbody>{renderZadatak()}</tbody>
                </Table>
              </Col>
            </Row>
          </Col>
        </div>
      );

}


export default Linije;