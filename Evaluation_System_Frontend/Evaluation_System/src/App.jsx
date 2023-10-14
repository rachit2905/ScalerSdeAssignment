import React, { useState } from "react";
import StudentDataTable from "./Components/StudentDataTable";
import FetchSelectedStudents from "./Components/FetchSelectedStudents";

function App() {
  const [showStudentDataTable, setShowStudentDataTable] = useState(true);

  return (
    <div>
      {showStudentDataTable ? <StudentDataTable /> : <FetchSelectedStudents />}
      <button
        onClick={() => setShowStudentDataTable(!showStudentDataTable)}
        style={{ marginTop: "8px" }}
      >
        Toggle View
      </button>
    </div>
  );
}

export default App;
