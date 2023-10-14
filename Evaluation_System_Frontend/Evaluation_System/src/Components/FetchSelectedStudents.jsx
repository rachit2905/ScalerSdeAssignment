import React, { useState, useEffect } from "react";
import axios from "axios";
import StudentDetails from "./StudentDetails";
import Papa from "papaparse";
import { Button, Box } from "@mui/material";

const FetchMentor = () => {
  const [fetching, setFetching] = useState(true);
  const [studentDetails, setStudentDetails] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        // Fetch mentor details
        const mentorResponse = await axios.get(
          "http://localhost:8080/mentor/1"
        );
        const mentorDetails = mentorResponse.data;

        // Fetch student details based on assigned student IDs
        const studentDetailsPromises = mentorDetails.students_assigned.map(
          (studentId) => axios.get(`http://localhost:8080/student/${studentId}`)
        );
        const studentDetailsResponses = await Promise.all(
          studentDetailsPromises
        );
        const studentDetailsData = studentDetailsResponses.map(
          (response) => response.data
        );

        setStudentDetails(studentDetailsData);
        setFetching(false);
      } catch (error) {
        console.error("Error fetching data:", error);
        setFetching(false);
      }
    };

    fetchData();
  }, []);

  const exportToCSV = () => {
    if (studentDetails.length === 0) {
      alert("No data to export.");
      return;
    }

    const csvData = studentDetails.map((student) => ({
      Student_ID: student.student_id,
      Student_Name: student.name,
      Final_Evaluation_Submitted: student.is_evaluation_final ? "Yes" : "No",
      Idea: student.evaluations[0] > 0 ? student.evaluations[0] : 0,
      Execution: student.evaluations[1] > 0 ? student.evaluations[1] : 0,
      Innovation: student.evaluations[2] > 0 ? student.evaluations[2] : 0,
      Viva: student.evaluations[3] > 0 ? student.evaluations[3] : 0,
      Report_Submission:
        student.evaluations[4] > 0 ? student.evaluations[4] : 0,
      Total_Marks:
        (student.evaluations[0] > 0 ? student.evaluations[0] : 0) +
        (student.evaluations[1] > 0 ? student.evaluations[1] : 0) +
        (student.evaluations[2] > 0 ? student.evaluations[2] : 0) +
        (student.evaluations[3] > 0 ? student.evaluations[3] : 0) +
        (student.evaluations[4] > 0 ? student.evaluations[4] : 0),
    }));
    const csv = Papa.unparse(csvData);
    const blob = new Blob([csv], { type: "text/csv" });
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement("a");
    a.style.display = "none";
    a.href = url;
    a.download = "student_details.csv";
    document.body.appendChild(a);
    a.click();
    window.URL.revokeObjectURL(url);
  };

  return (
    <div>
      <Box display="flex" justifyContent="space-between">
        <Button onClick={exportToCSV} variant="outlined" color="primary">
          Export as CSV
        </Button>
      </Box>

      <h2>Student Details</h2>
      {fetching ? (
        "Fetching..."
      ) : (
        <StudentDetails studentDetails={studentDetails} />
      )}
    </div>
  );
};

export default FetchMentor;
