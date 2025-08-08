import React, { useEffect, useState, useMemo } from 'react';
import { Doughnut } from 'react-chartjs-2';
import {
    Chart as ChartJS,
    ArcElement,
    Tooltip,
    Legend,
    Title
} from 'chart.js';
import {
    getSoftDeletedDoctorCount,
    getSoftDeletedPatientCount,
    getSoftDeletedNurseCount
} from '../../services/requests/softDeleteCounts';

ChartJS.register(ArcElement, Tooltip, Legend, Title);

const AdminDashboard = () => {
    const [counts, setCounts] = useState({ doctors: 0, patients: 0, nurses: 0 });
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchCounts = async () => {
            try {
                const [doctorCount, patientCount, nurseCount] = await Promise.all([
                    getSoftDeletedDoctorCount(),
                    getSoftDeletedPatientCount(),
                    getSoftDeletedNurseCount()
                ]);

                setCounts({
                    doctors: doctorCount,
                    patients: patientCount,
                    nurses: nurseCount
                });
            } catch (error) {
                setError('Failed to fetch data');
                console.error('Error fetching counts:', error);
            } finally {
                setLoading(false);
            }
        };

        fetchCounts();
    }, []);

    const data = useMemo(() => ({
        labels: ['Doctors', 'Patients', 'Nurses'],
        datasets: [
            {
                label: 'Totally',
                data: [counts.doctors, counts.patients, counts.nurses],
                backgroundColor: [
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(255, 159, 64, 0.2)'
                ],
                borderColor: [
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)'
                ],
                borderWidth: 1,
            },
        ],
    }), [counts]);

    const options = {
        responsive: true,
        plugins: {
            legend: {
                position: 'top',
            },
            title: {
                display: true,
            },
        },
    };

    if (loading) {
        return <p>Loading data...</p>;
    }

    if (error) {
        return <p>{error}</p>;
    }

    return (
        <div style={{ width: '50%', margin: 'auto', textAlign: 'center' }}>
            <h2>Admin Dashboard</h2>
            <Doughnut data={data} options={options} />
        </div>
    );
};

export default AdminDashboard;
