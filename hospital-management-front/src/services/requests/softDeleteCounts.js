import api from "../axios";

export const getSoftDeletedDoctorCount = async () => {
    try {
        const response = await api.get('/doctor/count');
        return response.data;
    } catch (error) {
        console.error('Error fetching soft-deleted doctor count:', error);
        throw error;
    }
};

export const getSoftDeletedPatientCount = async () => {
    try {
        const response = await api.get('/patient/count');
        return response.data;
    } catch (error) {
        console.error('Error fetching soft-deleted patient count:', error);
        throw error;
    }
};

export const getSoftDeletedNurseCount = async () => {
    try {
        const response = await api.get('/nurse/count');
        return response.data;
    } catch (error) {
        console.error('Error fetching soft-deleted nurse count:', error);
        throw error;
    }
};
