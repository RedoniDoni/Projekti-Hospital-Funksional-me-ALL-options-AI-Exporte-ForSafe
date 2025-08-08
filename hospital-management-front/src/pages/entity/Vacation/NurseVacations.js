import { Spin, Table } from "antd";
import { useEffect, useState } from "react";
import * as XLSX from "xlsx";
import { fetchVacationsByNurse } from "../../../services/requests/vacation";


const NurseVacations = () => {
  const [vacations, setVacations] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const loadNurseVacations = async () => {
      try {
        const response = await fetchVacationsByNurse();
        // Konverto startDate dhe endDate në data të formatizuara
        const transformedVacations = response.data.map((vacation) => ({
          ...vacation,
          startDate: new Date(vacation.startDate[0], vacation.startDate[1] - 1, vacation.startDate[2], vacation.startDate[3], vacation.startDate[4]),
          endDate: new Date(vacation.endDate[0], vacation.endDate[1] - 1, vacation.endDate[2], vacation.endDate[3], vacation.endDate[4]),
        }));
        setVacations(transformedVacations); // Vendos të dhënat e transformuara
      } catch (error) {
        console.error("Gabim gjatë ngarkimit të vacation për infermierët:", error);
      } finally {
        setLoading(false);
      }
    };

    loadNurseVacations();
  }, []);

  const columns = [
    {
      title: "Data e Fillimit",
      dataIndex: "startDate",
      key: "startDate",
      render: (text) => text.toLocaleDateString(), // Shfaq datën në formatin lokal
    },
    {
      title: "Data e Mbarimit",
      dataIndex: "endDate",
      key: "endDate",
      render: (text) => text.toLocaleDateString(), // Shfaq datën në formatin lokal
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
  ];

  
  const handleExportToExcel = () => {
  const exportData = vacations.map(({ startDate, endDate, reason, certification }) => ({
    "Data e Fillimit": startDate.toLocaleDateString(),
    "Data e Mbarimit": endDate.toLocaleDateString(),
    "Arsyeja": reason,
    "Certifikata": certification,
  }));

  const worksheet = XLSX.utils.json_to_sheet(exportData);
  const workbook = XLSX.utils.book_new();
  XLSX.utils.book_append_sheet(workbook, worksheet, "Nurse Vacations");
  XLSX.writeFile(workbook, "NurseVacations.xlsx");
};


  return (
    <div>
      <h2>Pushimet nga Infermierët</h2>
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

export default NurseVacations;

