import { Spin, Table } from "antd";
import { useEffect, useState } from "react";
import * as XLSX from "xlsx";
import { fetchVacationsByDoctor } from "../../../services/requests/vacation";


const DoctorVacations = () => {
  const [vacations, setVacations] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const loadDoctorVacations = async () => {
      try {
        const response = await fetchVacationsByDoctor();
        
        // Konverto startDate dhe endDate nga array në Date objekt
        const transformedVacations = response.data.map((vacation) => ({
          ...vacation,
          startDate: new Date(vacation.startDate[0], vacation.startDate[1] - 1, vacation.startDate[2], vacation.startDate[3], vacation.startDate[4]),
          endDate: new Date(vacation.endDate[0], vacation.endDate[1] - 1, vacation.endDate[2], vacation.endDate[3], vacation.endDate[4]),
        }));
        
        setVacations(transformedVacations); // Vendos të dhënat e transformuara
      } catch (error) {
        console.error("Gabim gjatë ngarkimit të vacation për doktorët:", error);
      } finally {
        setLoading(false);
      }
    };

    loadDoctorVacations();
  }, []);

  const columns = [
    {
      title: "Data e Fillimit",
      dataIndex: "startDate",
      key: "startDate",
      render: (text) => text.toLocaleDateString(),
    },
    {
      title: "Data e Mbarimit",
      dataIndex: "endDate",
      key: "endDate",
      render: (text) => text.toLocaleDateString(),
    },
    {
      title: "Arsyeja",
      dataIndex: "reason",
      key: "reason",
    },
    {
      title: "Certifikata",
      dataIndex: "certification",
      key: "certification",
    },
    {
      title: "Doctor ID",
      dataIndex: "doctorId",
      key: "doctorId",
      render: (doctorId) => (doctorId ? doctorId : "N/A"), // Shfaq "N/A" nëse doctorId është null
    },
  ];

  const handleExportToExcel = () => {
  // Përgatit të dhënat për eksport, duke formatuar datat në string
  const exportData = vacations.map(({ startDate, endDate, reason, certification, doctorId }) => ({
    "Data e Fillimit": startDate.toLocaleDateString(),
    "Data e Mbarimit": endDate.toLocaleDateString(),
    "Arsyeja": reason,
    "Certifikata": certification,
    "Doctor ID": doctorId ? doctorId : "N/A",
  }));

  const worksheet = XLSX.utils.json_to_sheet(exportData);
  const workbook = XLSX.utils.book_new();
  XLSX.utils.book_append_sheet(workbook, worksheet, "Doctor Vacations");
  XLSX.writeFile(workbook, "DoctorVacations.xlsx");
};


  return (
    <div>
      <h2>Pushimet nga Doktorët</h2>
      <div style={{ marginBottom: '15px' }}>
  <button
    style={{
      backgroundColor: '#4CAF50',
      color: 'white',
      padding: '8px 12px',
      border: 'none',
      borderRadius: '5px',
      cursor: 'pointer',
      transition: '0.3s'
    }}
    onClick={handleExportToExcel}
  >
    Export to Excel
  </button>
</div>

      {loading ? (
        <Spin />
      ) : (
        <Table
          dataSource={vacations}
          columns={columns}
          rowKey={(record) => record.id}
        />
      )}
    </div>
  );
};

export default DoctorVacations;

