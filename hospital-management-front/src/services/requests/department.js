import api from "../axios";

export const fetchAllDepartments = async () => {
  try {
    const response = await api.get('/departament/all');
    return response.data;
  } catch (error) {
    console.error('Error fetching departments:', error);
    throw error;
  }
};

// export const createDepartment = async (departmentData) => {
//   try {
//     const response = await api.post('/departament/create', departmentData);
//     return response.data;
//   } catch (error) {
//     console.error('Error creating department:', error);
//     throw error;
//   }
// };

// export const updateDepartment = async (id, departmentData) => {
//   try {
//     const response = await api.put(`/department/update/${id}`, departmentData);
//     return response.data;
//   } catch (error) {
//     console.error('Error updating department:', error);
//     throw error;
//   }
// };

// export const deleteDepartment = async (id) => {
//   try {
//     const response = await api.delete(`/department/delete/${id}`);
//     return response.data;
//   } catch (error) {
//     console.error('Error deleting department:', error);
//     throw error;
//   }
// };
